export default function WelcomeScreen({ onStart, loading }) {
  return (
    <div className="flex-1 flex flex-col justify-center p-8 gap-4">
      <button
        onClick={() => onStart('TECHNICAL')}
        disabled={loading}
        className="p-6 bg-slate-700 hover:bg-slate-600 rounded-lg transition-all border border-slate-600 hover:border-blue-500 group text-left"
      >
        <h3 className="text-xl font-semibold mb-2 text-white group-hover:text-blue-400">Technical Interview</h3>
        <p className="text-slate-400 text-sm">Java, Spring Boot, System Design</p>
      </button>

      <button
        onClick={() => onStart('BEHAVIORAL')}
        disabled={loading}
        className="p-6 bg-slate-700 hover:bg-slate-600 rounded-lg transition-all border border-slate-600 hover:border-purple-500 group text-left"
      >
        <h3 className="text-xl font-semibold mb-2 text-white group-hover:text-purple-400">Behavioral Interview</h3>
        <p className="text-slate-400 text-sm">Soft skills, Conflict resolution</p>
      </button>
    </div>
  )
}