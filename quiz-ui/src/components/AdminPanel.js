import React, { useState } from 'react'

const AdminPanel = () => {
  const [title, setTitle] = useState('');
  const [questions, setQuestions] = useState([]);
  const [qText, setQText] = useState('');
  const [qType, setQType] = useState('MCQ');
  const [options, setOptions] = useState('');
  const [correctAnswer, setCorrectAnswer] = useState('');

  const addQuestion = () => {
    const opts = options.split(',').map(o => o.trim);
    setQuestions([...questions, {
      text:qText,
      type:qType,
      options: opts,
      correctAnswer
    }]);
    setQText('');
    setQType('MCQ');
    setOptions('');
    setCorrectAnswer('');
  };

  const submitQuiz = async () => {
    const payload = {title, questions};
    const res = await fetch ('http://localhost:8080/api/v1/quizzes',{
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(payload)
    });
    if(res.ok){
      alert('Quiz Created successfully');
      setTitle('');
      setQuestions([]);
    }else{
      alert('Error creating quiz');
    }
  };

  return (
    <div style={{padding: '10px'}}>
      <h2>Create Quiz</h2>
      <input
        type='text'
        placeholder='Quiz Title'
        value={title}
        onChange={e => setTitle(e.target.value)}
        style={{width:'200px', marginBottom: '10px'}}
        />
        <h3>Add Question</h3>
        <input
          type='text'
          placeholder='Question text'
          value={qText}
          onChange={e=>setQText(e.target.value)}
          /><br />
          <select value={qType} onChange={e=>setQType(e.target.value)}>
            <option value="MCQ">MCQ</option>
            <option value="TRUE_FALSE">True/Fasle</option>
            <option value="TEXT">Text</option>
          </select><br />
          {qType === 'MCQ' && <input type='text' placeholder='Options comma separated' value={options}
          onChange={e => setOptions(e.target.value)} />}
          <br/>
          <input type='text' placeholder='Correct Answer' value={correctAnswer} 
          onChange={e=> setCorrectAnswer(e.target.value)} />
          <br/>
          <button onClick={addQuestion} style={{margin: '10px'}}>Add Question</button>
          <button onClick={submitQuiz} style={{margin: '10px'}}>Submit Quiz</button>


          <h3>Questions Preview</h3>
          <ul>
            {questions.map((q, idx) => (
              <li key={idx}>{q.text} ({q.type})</li>
            ))}
          </ul>
    </div>
  );

};

export default AdminPanel