import { useState } from 'react'
import WelcomeScreen from './components/WelcomeScreen'
import ChatInterface from './components/ChatInterface'

function App() {
  const [interview, setInterview] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const startInterview = async (type) => {
    setLoading(true)
    setError(null)
    try {
      const response = await fetch('http://localhost:8080/api/interviews/start', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: 'Practice Session', type: type }),
      })
      
      if (!response.ok) throw new Error('Failed to connect to backend')
      
      const data = await response.json()
      setInterview(data)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center p-4">
      <div className="max-w-2xl w-full bg-slate-800 rounded-xl shadow-2xl overflow-hidden border border-slate-700 flex flex-col h-[80vh]">
        
        <div className="p-4 border-b border-slate-700 bg-slate-800/50">
          <h1 className="text-xl font-bold text-center text-transparent bg-clip-text bg-gradient-to-r from-blue-400 to-purple-500">
            AI Interviewer
          </h1>
        </div>

        {error && (
          <div className="p-2 bg-red-500/10 text-red-400 text-xs text-center border-b border-red-500/20">
            {error}
          </div>
        )}

        {!interview ? (
          <WelcomeScreen onStart={startInterview} loading={loading} />
        ) : (
          <ChatInterface 
            interviewId={interview.interviewId} 
            firstQuestion={interview.firstQuestion}
            onRestart={() => setInterview(null)}
          />
        )}

      </div>
    </div>
  )
}

export default App