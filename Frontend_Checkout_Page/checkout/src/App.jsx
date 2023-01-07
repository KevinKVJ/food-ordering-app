import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import './baga.css'


function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <div>
        {/* <a href="https://vitejs.dev" target="_blank">
          <img src="/vite.svg" className="logo" alt="Vite logo" />
        </a>
        <a href="https://reactjs.org" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a> */}
      </div>
      
      <div className='wrapper'>
        <div className='broadage'>
          baga
        </div>
        <div>
          <div className='title'>
            baga
          </div>
          <div className='content'>
            <div>
              <h3>Checkout</h3>
            </div>  
          </div>
        </div>
        <div className='broadage'>
          baga
        </div>
      </div>



      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </div>
  )
}

export default App
