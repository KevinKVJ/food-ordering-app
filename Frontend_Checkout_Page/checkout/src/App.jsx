import { useState } from 'react'
import reactLogo from './assets/react.svg'
import SvgIcon from './SvgIcon'
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

      <div className='background'>

        <div className='title'>
          baga
        </div>
        <div className='content'>

          <div className='checkout'>Checkout</div>

          <div className='block'>
            <div className='small-title'>Address</div>
            <div className='payment-method'>
              <div>
                <SvgIcon name='mcpic' width={100} height={100} color='#646cff' />

              </div>

              <div>
                <div className='address'>Office</div>
                <div className='address'>Adi Sucipto St. No.23, Solo,Central Java, Indonesia</div>
              </div>

            </div>
          </div>

          <div className='block'>
            <div className='small-title'>Payment Method</div>
            <div className='payment-method'>
              <div>
                <SvgIcon name='master' width={100} height={100} color='#646cff' />

              </div>

              <div>
                <div>Master Card</div>
                <div>********7896</div>
              </div>
            </div>
          </div>

          <div className='block'>
            <div className='wrapper'>
              <div className='sum'>Shipping cost</div>
              <div className='amount'>36.00</div>
            </div>

            <div className='wrapper'>
              <div className='sum'>Sub total</div>
              <div className='amount'>3.70</div>
            </div>

            <div className='wrapper'>
              <div className='sum'>Total</div>
              <div className='amount'>39.70</div>
            </div>
          </div>

          <div className='block'>
            <div className='small-title'>Add Voucher or Promo Code</div>
            <div className='voucher-box'> add voucher or promo code here</div>
          </div>


          <button className='place-order-button'>Place order</button>
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
