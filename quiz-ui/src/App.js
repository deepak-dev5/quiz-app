import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import AdminPanel from './components/AdminPanel';
import TakeQuiz from './components/TakeQuiz';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Home />}/>
        <Route path='/admin' element={<AdminPanel/>} />
        <Route path='/quiz' element={<TakeQuiz/>} />
      </Routes>
    </Router>
  );
}

export default App;
