import 'normalize.css';
import './index.scss';
import 'virtual:windi.css';
import 'virtual:svg-icons-register';

import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './App';

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);
