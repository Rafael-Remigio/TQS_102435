import logo from './logo.svg';
import './App.css';
import {useRef} from 'react';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route} from "react-router-dom";
function App() {

  const inputRef = useRef(null);


  const [latitude, setLatitude] = useState([]);
  const [longitude, setLongitude] = useState([]);


  const apiKey = process.env.REACT_APP_API_KEY

  const handleSubmit = (event) => {
    var url = "https://api.opencagedata.com/geocode/v1/json?key="+apiKey+"&q="+inputRef.current.value;
    console.log(url)
    window.location.replace('/results');    
    /*
    axios
    .get(url)
    .then((response) => {
        setLatitude(response.data.results[0].geometry.lat);
        setLongitude(response.data.results[0].geometry.lng);
        console.log(latitude.toString() + "     " + longitude.toString())
      })
      .catch((err) => {
        console.log(err);
      });
      */

    


    event.preventDefault();
  }


  const Home = () => {
    return (
    <div className="App">
      <header className="App-header">
        <div>
          <h2>Open-Air</h2>
          <h4>Search the weather for a specific location</h4>
        </div>

        <form onSubmit={handleSubmit}>
          <label>
            Location:
            <input ref={inputRef} id="location" type="text" name="name" />
          </label>
          <input type="submit" value="Submit" />
        </form>
      </header>
    </div>
  )
  };
  const Results = () =>{

    alert(latitude)
    return (
      <div>
        <h1>
          Results

        </h1>
      </div>
    )
  };


  return (
    <BrowserRouter>
    <div>
      <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/results" element={<Results />} />
      </Routes>
    </div>
    </BrowserRouter>

  );



}




export default App;
