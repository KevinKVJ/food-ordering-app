import React, { useState, useEffect } from 'react';
import Flex from './components/FlexLayout/FlexLayout';

function FuncExample() {
    const [count, setCount] = useState(0);
    useEffect(() => {
        setTimeout(() => {
            console.log('FuncExample', count);
        }, 10000);
    }, []);
    return (
        <div>
            <p>FuncExample clicked {count} times</p>
            <button onClick={() => setCount(count + 1)}>Click me</button>
        </div>
    );
}

class ClassExample extends React.Component {
    state = {
        count: 0,
    };
    componentDidMount() {
        setTimeout(() => {
            console.log('ClassExample', this.state.count);
        }, 10000);
    }
    render() {
        const { count } = this.state;
        return (
            <div>
                <p>ClassExample clicked {count} times</p>
                <button onClick={() => this.setState({ count: count + 1 })}>Click me</button>
            </div>
        );
    }
}

export default function App() {
    return (
        <div className='555'>
            <Flex wrap>
                <FuncExample />
                <ClassExample />
            </Flex>
        </div>
    );
}
// ,
//     "browserslist": [
//         "Android 4.1",
//         "iOS 7.1",
//         "Chrome > 31",
//         "ff > 31",
//         "ie >= 8",
//         "> 1%"
//     ]