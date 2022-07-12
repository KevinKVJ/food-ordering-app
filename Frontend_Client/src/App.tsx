import { useState } from 'react';
import logo from './logo.svg';
import './App.scss';
// import Test from "./Test1"
import Swiper from '@/components/MySwiper/Swiper1';
import NavBar from '@/views/common/NavBar/NavBar';

function App() {
    const [count, setCount] = useState(0);

    return (
        <div className='App'>
            <NavBar></NavBar>
            <Swiper></Swiper>
        </div>
    );
}

export default App;
