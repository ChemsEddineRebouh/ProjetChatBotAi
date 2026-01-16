import { useState, useRef, useEffect } from 'react'

export default function ChatInterface({ interviewId, firstQuestion, onRestart }) {
  const [messages, setMessages] = useState([{ role: 'ai', content: firstQuestion }])
  const [inputMessage, setInputMessage] = useState('')
  const [loading, setLoading] = useState(false)
  const messagesEndRef = useRef(null)

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" })
  }

  useEffect(() => {
    scrollToBottom()
  }, [messages])

 const handleSend = async (messageText) => {
    if (!messageText.trim()) return

    const aiMessages = messages.filter(m => m.role === 'ai');
    const lastAiMessage = aiMessages.length > 0 ? aiMessages[aiMessages.length - 1].content : "";
    
    console.log("Sending Context to Backend:", lastAiMessage); 

    setMessages(prev => [...prev, { role: 'user', content: messageText }])
    setInputMessage('')
    setLoading(true)

    try {
      const response = await fetch('http://localhost:8080/api/interviews/chat', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 
          interviewId: interviewId, 
          userMessage: messageText,
          context: lastAiMessage
        }),
      })

      if (!response.ok) throw new Error('Failed to send message')

      const data = await response.json()
      setMessages(prev => [...prev, { role: 'ai', content: data.response }])
    } catch (err) {
      console.error(err)
      setMessages(prev => [...prev, { role: 'ai', content: "Error: Could not reach server." }])
    } finally {
      setLoading(false)
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    handleSend(inputMessage)
  }

  return (
    <>
      <div className="flex-1 overflow-y-auto p-4 space-y-4 scrollbar-thin scrollbar-thumb-slate-600">
        {messages.map((msg, idx) => (
          <div key={idx} className={`flex ${msg.role === 'user' ? 'justify-end' : 'justify-start'}`}>
            <div className={`max-w-[85%] rounded-lg p-3 ${
              msg.role === 'user' 
                ? 'bg-blue-600 text-white rounded-br-none' 
                : 'bg-slate-700 text-slate-200 rounded-bl-none'
            }`}>
              <p className="text-sm leading-relaxed whitespace-pre-wrap font-mono">{msg.content}</p>
            </div>
          </div>
        ))}
        {loading && (
          <div className="flex justify-start animate-pulse">
            <div className="bg-slate-700 p-3 rounded-lg rounded-bl-none">
              <span className="text-slate-400 text-sm">Thinking...</span>
            </div>
          </div>
        )}
        <div ref={messagesEndRef} />
      </div>

      <div className="border-t border-slate-700 bg-slate-800 p-4">
        <div className="flex gap-2 mb-3 justify-center">
          {['A', 'B', 'C', 'D'].map((choice) => (
            <button
              key={choice}
              onClick={() => handleSend(`Option ${choice}`)}
              disabled={loading}
              className="px-6 py-2 bg-slate-700 hover:bg-blue-600 text-white rounded font-bold transition-colors border border-slate-600 hover:border-blue-500"
            >
              {choice}
            </button>
          ))}
        </div>

        <form onSubmit={handleSubmit} className="flex gap-2">
          <input
            type="text"
            value={inputMessage}
            onChange={(e) => setInputMessage(e.target.value)}
            placeholder="Type manually or click above..."
            className="flex-1 bg-slate-900 border border-slate-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:border-blue-500"
            disabled={loading}
          />
          <button 
            type="submit" 
            disabled={loading || !inputMessage.trim()}
            className="bg-blue-600 hover:bg-blue-500 text-white px-6 py-2 rounded-lg font-medium transition-colors"
          >
            Send
          </button>
        </form>
        
        <button 
            onClick={onRestart}
            className="w-full mt-2 py-1 text-xs text-slate-500 hover:text-slate-300"
        >
            End Interview
        </button>
      </div>
    </>
  )
}