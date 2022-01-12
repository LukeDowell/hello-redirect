import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";

function App() {
  const [requestTarget, setRequestTarget] = useState("http://localhost:8080/legacy/content")

  const sendRequest = () => {
    axios.get(requestTarget).then((response) => {
      console.log("Response", response)
    })
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <button onClick={sendRequest}>Send Request</button>
      </header>
    </div>
  );
}

export default App;
