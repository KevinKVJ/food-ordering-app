import { useState } from 'react'
import SvgIcon from './SvgIcon'
import './baga.css'
// import reactLogo from './assets/react.svg'
// import './App.css'


function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <div className='background'>

        <div className='title'>
          <div className="back-button">
            <div></div>
            <div>back</div>
          </div>
          <div className='shopping cart'>shopping</div>
        </div>


        <div className='content'>

          <div className='checkout'>Checkout</div>
          <div className='horizontal-ruler'></div>
          <div className='block'>
            <div className='small-title'>Address</div>
            <div className='address-display'>
              <div>
                <SvgIcon name='mcpic' width={100} height={100} color='#646cff' />

              </div>

              <div>
                <div className='address-line1'>Office</div>
                <div className='address-line2'>Adi Sucipto St. No.23, Solo,Central Java, Indonesia</div>
              </div>

            </div>
          </div>

          <div className='horizontal-ruler'></div>
          <div className='block'>
            <div className='small-title'>Payment Method</div>
            <div className='mastercard'>
              <div>
                <SvgIcon name='master' width={100} height={100} color='#646cff' />

              </div>

              <div className='master-text'>
                <div>Master Card</div>
                <div>********7896</div>
              </div>
            </div>
          </div>

          <div className='horizontal-ruler'></div>
          <div className='block'>
            <div className='wrapper'>
              <div className='sum'>Shipping cost</div>
              <div className='amount'>36.00</div>
            </div>

            <div className='wrapper'>
              <div className='sum'>Sub total</div>
              <div className='amount'>3.70</div>
            </div>

            <div className='dashed-ruler'></div>
            <div className='wrapper'>
              <div className='sum'>Total</div>
              <div className='amount'>39.70</div>
            </div>
          </div>

          <div className='horizontal-ruler'></div>
          <div className='block'>
            <div className='small-title'>Add Voucher or Promo Code</div>
            <div className='voucher-box'> <div className='promo-txt'>Add voucher or promo code here</div></div>
          </div>


          <button className='place-order-button'>Place order</button>
        </div>

      </div>
    </div>
  )
}

export default App
