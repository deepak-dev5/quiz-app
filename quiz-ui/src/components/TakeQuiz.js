import React, { useEffect, useState} from 'react'

const TakeQuiz = () => {
  const [quizzes, setQuizzes] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [answers, setAnswers] = useState({});
  const [score, setScore] = useState(0);
  const [showScore, setShowScore] = useState(false);

  useEffect(()=>{
    fetch('http://localhost:8080/api/v1/quizzes')
    .then(res => res.json())
    .then(data => setQuizzes(data));
  }, []);


  if(quizzes.length === 0) return <div style={{textAlign: 'center'}}>Loading Quizzes...</div>

  if(showScore) return <div style={{textAlign: 'center'}}><h2>Final score: {score}</h2></div>

  const currentQuiz = quizzes[currentIndex];

  const handleAnswerChange = (questionId, value) => {
    setAnswers({...answers, [questionId]: value})
  };

  const handleSubmit = async () => {
    const res = await fetch(`http://localhost:8080/api/v1/quizzes/${currentQuiz.id}/submit`,{
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(answers)
  });

  const data = await res.json();

  setScore(prev => prev+data.score);

  if(currentIndex+1<quizzes.length){
    setCurrentIndex(currentIndex+1);
    setAnswers({});
  }else{
    setShowScore(true);
  }
};

return (
  <div>
    <h2>{currentQuiz.title}</h2>
    {currentQuiz.questions.map(q=>(
      <div key={q.id}>
        <p>{q.text}</p>
        {q.type === 'MCQ' ? q.options.map((opt, idx) => (
          <div key= {idx}>
            <input
              type='radio'
              name={q.id}
              value={opt}
              checked={answers[q.id] === opt}
              onChange={e=>handleAnswerChange(q.id, e.target.value)}
              />
              <label>{opt}</label>
              </div>
        )): (
          <input
            type='text'
            value={answers[q.id] || ''}
            onChange={e=>handleAnswerChange(q.id, e.target.value)}
            />
        )}
        </div>
    ))}
    <button onClick={handleSubmit}>Submit</button>
  </div>
);

};

export default TakeQuiz