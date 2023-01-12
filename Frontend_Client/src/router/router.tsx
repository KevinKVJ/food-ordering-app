import { RouteProps } from 'react-router-dom';

import User from '@/views/common/NavBar/User';
import IndexPage from '@/views/IndexPage/IndexPage';
import Login from '@/views/LoginAndSignup/Login';
import Signup from '@/views/LoginAndSignup/Signup';

const routeInfo: RouteProps[] = [
    {
        path: '/',
        element: <IndexPage />,
    },
    {
        path: '/user',
        element: <User></User>,
    },
    {
        path: '/login',
        element: <Login />,
    },
    {
        path: '/signup',
        element: <Signup />,
    },
];

export default routeInfo;
