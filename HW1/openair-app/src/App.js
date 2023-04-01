import logo from './logo.svg';
import './App.css';
import {useRef} from 'react';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route} from "react-router-dom";
import Row from 'react-bootstrap/Row';

function App() {

  const inputRef = useRef(null);
  const Latitude = useRef(null);
  const Longitude = useRef(null);


  const apiKey = process.env.REACT_APP_API_KEY

  const handleSubmit = (event) => {
    var url = "https://api.opencagedata.com/geocode/v1/json?key="+apiKey+"&q=";
    console.log(url)
    window.location.replace('/results&location='+inputRef.current.value);    

    event.preventDefault();
  }

  const handleSubmitCoord = (event) => {
    window.location.replace('/results&lat='+Latitude.current.value+"&lng="+Longitude.current.value);    

    event.preventDefault();
  }


  const Home = () => {
    return (
    <div className="App">
      <header className="App-header">
        <div>
          <h2>Open-Air</h2>
          <h4>Search the weather for a specific addres</h4>
        </div>

        <form onSubmit={handleSubmit}>
          <label>
            Location:
            <input ref={inputRef} id="location" type="text" name="name" />
          </label>
          <input type="submit" value="Submit" />
        </form>

        <h4>Search the weather for a specific location with coordinates</h4>
        <form onSubmit={handleSubmitCoord}>
          <Row>
            <label>
              Latitude:
              <input ref={Latitude} id="location" type="text" name="name" />
            </label>
          </Row>
          <Row>
            <label>
              Longitude:
              <input ref={Longitude} id="location" type="text" name="name" />
            </label>
          </Row>
          <input type="submit" value="Submit" />
        </form>
      </header>
    </div>
  )
  };
  const Results = () =>{


    const fetchData = (event) => {
      /*var url = "https://api.opencagedata.com/geocode/v1/json?key="+apiKey+"&q=";
      console.log(url)
      window.location.replace('/results&location='+inputRef.current.value);    
  
      event.preventDefault();*/
    }

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
    <Routes>
      <Route
        path="/"
        element={<Home />}
      />
      <Route
        path="results/:id"
        element={<Results />}
      />
    </Routes>
  </BrowserRouter>

  );



}




export default App;
