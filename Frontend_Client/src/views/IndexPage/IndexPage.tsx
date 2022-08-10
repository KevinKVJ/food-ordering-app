import { useState } from 'react';

import Drawer from '@/components/Drawer/Drawer';
import Footer from '@/views/common/Footer/Footer';
import NavBar from '@/views/common/NavBar/NavBar';

// import MainContent from './MainContent/MainContent';

const IndexPage = () => {
    const [drawerSwitch, setDrawerSwitch] = useState<boolean>(false);
    return (
        <>
            <div className='navbar'>
                <NavBar />
            </div>
            <div className='main-content'>{/* <MainContent /> */}</div>
            <div className='footer'>
                <Footer />
            </div>
            <button onClick={() => setDrawerSwitch(true)}>open drawer</button>
            <Drawer
                activeSwitch={drawerSwitch}
                onClose={() => {
                    setDrawerSwitch(false);
                    console.log('lululala');
                }}>
                <div>666666</div>
            </Drawer>
        </>
    );
};
export default IndexPage;
