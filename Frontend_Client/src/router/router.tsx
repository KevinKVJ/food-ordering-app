import { RouteProps } from 'react-router-dom';

import User from '@/views/common/NavBar/User';
import IndexPage from '@/views/IndexPage/IndexPage';

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
