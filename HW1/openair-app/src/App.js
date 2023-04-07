import logo from './logo.svg';
import './App.css';
import {useRef} from 'react';
import axios from 'axios';
import { useState, useEffect } from 'react';
import Table from 'react-bootstrap/Table';

function App() {


  const [stats, setStats] = useState(null);
  const [cacheInfo, setCache] = useState(null);

  const inputRef = useRef(null);

  const handleSubmit = (event) => {

    var url = "http://localhost:8080/weather?local="+inputRef.current.value;
    console.log(url)


    axios.get(url)
        .then(response => {

            const data = response.data;
            setStats(data)
            setCache(null)

        })
        .catch(error => {
            setStats(null)
            setCache(null)
            console.error('There was an error!', error);
            alert("Service Unavailable, try again later")
        });

    event.preventDefault();
  }

  const handleCacheRequest = (event) => {

    var url = "http://localhost:8080/cacheInfo";
    console.log(url)


    axios.get(url)
        .then(response => {

            const data = response.data;
            setCache(data)
            setStats(null)

        })
        .catch(error => {
            setStats(null)
            setCache(null)
            console.error('There was an error!', error);
            alert("Service Unavailable, try again later")
        });

    event.preventDefault();
  }


  const Home = () => {
    return (
    <div className="App">
      <header className="App-header">
        <div>
          <h2>Open-Air</h2>
          <h4>Get cache details  <button onClick={handleCacheRequest}>Click Me</button></h4>
          <h4>Search the weather for a specific addres</h4>
        </div>

        <form onSubmit={handleSubmit}>
          <label>
            Location:
            <input ref={inputRef} id="location" type="text" name="name" />
          </label>
          <input type="submit" value="Submit" />
        </form>

        <Results />
      </header>
        
    </div>
  )
  };
  const Results = () =>{

    if (stats!= null){
      return (
        <div>
          <h3>
            Results for {stats.location} ({stats.coords.lat},{stats.coords.lgn})
          </h3>
          <center >
          <Table striped bordered hover size="sm" cellPadding={"15px"} style={{textAlign: 'center'}}>
            <thead>
              <tr>
                <th>#</th>
                <th>value</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>CO</td>
                <td>{stats.stats.co}</td>
              </tr>
              <tr>
                <td>NO2</td>
                <td>{stats.stats.no2}</td>
              </tr>
              <tr>
                <td>PM25</td>
                <td>{stats.stats.pm25}</td>
              </tr>
              <tr>
                <td>PM10</td>
                <td>{stats.stats.pm10}</td>
              </tr>
            </tbody>
          </Table> 
          </center> 
        </div>
      )
    }

    if (cacheInfo != null){
      return <div>
              <h5>Cache Info</h5>
              <p>Accesses: {cacheInfo.acesses}</p>
              <p>Hits: {cacheInfo.hits}</p>
              <p>Misses: {cacheInfo.misses}</p>
            </div>
    }

    return <div></div>
  };


  return (   
    <div>
      <Home />
    </div> 
  );



}




export default App;
