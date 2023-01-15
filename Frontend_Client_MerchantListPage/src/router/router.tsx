import { RouteProps } from 'react-router-dom';

import IndexPage from '@/views/IndexPage/IndexPage';
import Log from '@/views/IndexPage/Log.jsx';

// import IndexPage from '@/views/IndexPage/IndexPage';

const routeInfo: RouteProps[] = [
    {
        path: '/',
        element: <IndexPage />,
    },
    {
        path: '/log',
        element: <Log />,
    },
];

export default routeInfo;
