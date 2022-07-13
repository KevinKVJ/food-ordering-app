import User from '@/views/common/NavBar/User';
import IndexPage from '@/views/IndexPage/IndexPage';
import { RouteProps } from 'react-router-dom';

const routeInfo: RouteProps[] = [
    {
        path: '/',
        element: <IndexPage />,
    },
    {
        path: '/user',
        element: <User></User>,
    },
];

export default routeInfo;
