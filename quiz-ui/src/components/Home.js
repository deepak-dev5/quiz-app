import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();

  return (
    <div style={{textAlign: 'center', marginTop: '70px'}}>
        <h1>Quiz App</h1>
        <button onClick={()=> navigate ('/admin')} style={{margin: '10px', padding: '10px'}}>Admin Panel</button>
        <button onClick={()=> navigate ('/quiz')} style={{margin: '10px', padding: '10px'}}>Take Quiz</button>
    </div>
  )
}

export default Home