import React, { useEffect, useState } from 'react';

const TakeQuiz = () => {
  const [quizzes, setQuizzes] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [answers, setAnswers] = useState({});
  const [score, setScore] = useState(0);
  const [showScore, setShowScore] = useState(false);
  const [correctAnswers, setCorrectAnswers] = useState([]);
  const [timer, setTimer] = useState(20);

    useEffect(() => {
    if (timer > 0) {
      const intervalId = setInterval(() => {
        setTimer((prevTimer) => prevTimer - 1);
      }, 1000);

      return () => clearInterval(intervalId);
    } else {
      handleFinalSubmit();
    }
  }, [timer]);

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/quizzes')
      .then((res) => res.json())
      .then((data) => setQuizzes(data));
  }, []);

  if (quizzes.length === 0) return <div style={{ textAlign: 'center' }}>Loading Quizzes...</div>;

  const currentQuiz = quizzes[0];

  const handleAnswerChange = (questionId, value) => {
    setAnswers((prev) => ({
      ...prev,
      [questionId]: value,
    }));
  };

  const handleSubmitAnswer = () => {
    const currentQuestion = currentQuiz.questions[currentIndex];

    if (
      (currentQuestion.type === 'MCQ' && !answers[currentQuestion.id]) ||
      (currentQuestion.type === 'TEXT' && (!answers[currentQuestion.id] 
        || answers[currentQuestion.id].trim() === ''))
    ) {
      alert('Please answer all the questions before submitting!');
      return;
    }

    if (currentIndex + 1 < currentQuiz.questions.length) {
      setCurrentIndex(currentIndex + 1);
    } else {
      handleFinalSubmit();
    }
  };

  const handleFinalSubmit = async () => {
    try {
      const res = await fetch(`http://localhost:8080/api/v1/quizzes/${currentQuiz.id}/submit`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ answers }),
      });

      if (!res.ok) {
        console.error('Error submitting quiz:', res.status, res.statusText);
        throw new Error('Failed to submit quiz');
      }

      const data = await res.json();
      console.log('Received response:', data);

      const quizScore = Number(data.score) || 0;
      setScore(prev => prev + quizScore);

      setCorrectAnswers(data.questionsWithCorrectAnswers);
      setShowScore(true);
    } catch (error) {
      console.error('Error occurred during quiz submission:', error.message);
      alert('Error occurred while submitting the quiz. Please try again later.');
    }
  };

  if(timer==0) {
    handleFinalSubmit();
    // setTimer(0);
  }

  return (
    <div>
      <div><p>Time left:: {timer}s</p></div>
      {!showScore ? (
        <div>
          <h2>{currentQuiz.title}</h2>

          {currentQuiz.questions[currentIndex] && (
            <div key={currentQuiz.questions[currentIndex].id}>
              <p>{currentQuiz.questions[currentIndex].text}</p>

              {currentQuiz.questions[currentIndex].type === 'MCQ' ? (
                currentQuiz.questions[currentIndex].options.map((opt, idx) => (
                  <div key={idx}>
                    <input
                      type="radio"
                      name={currentQuiz.questions[currentIndex].id}
                      value={opt}
                      checked={answers[currentQuiz.questions[currentIndex].id] === opt}
                      onChange={(e) => handleAnswerChange(currentQuiz.questions[currentIndex].id,
                         e.target.value)}
                    />
                    <label>{opt}</label>
                  </div>
                ))
              ) : (
                <input
                  type="text"
                  value={answers[currentQuiz.questions[currentIndex].id] || ''}
                  onChange={(e) => handleAnswerChange(currentQuiz.questions[currentIndex].id,
                     e.target.value)}
                />
              )}
              <br/>
              <button onClick={handleSubmitAnswer}>
                {currentIndex + 1 < currentQuiz.questions.length ? 'Next' : 'Submit'}
              </button>
            </div>
          )}
        </div>
      ) : (
        <div style={{ textAlign: 'center' }}>
          <h2>Final Score: {score}</h2>
          <h3>Correct Answers:</h3>
          <ul>
            {correctAnswers.map((q, qIdx) => (
              <li key={qIdx}>
                <strong>{q.text}</strong>: {q.correctAnswer}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default TakeQuiz;
