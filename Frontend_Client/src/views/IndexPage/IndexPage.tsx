import { useState } from 'react';

import Drawer from '@/components/Drawer/Drawer';
import Footer from '@/views/common/Footer/Footer';
import NavBar from '@/views/common/NavBar/NavBar';

import MainContent from './MainContent/MainContent';

const IndexPage = () => {
    const dSwitch = useState<boolean | undefined>(false);
    const [drawerSwitch, setDrawerSwitch] = dSwitch;
    return (
        <>
            <div className='navbar'>
                <NavBar />
            </div>
            <div className='main-content'>
                <MainContent />
            </div>
            <div className='footer'>
                <Footer />
            </div>
            <button onClick={() => setDrawerSwitch(true)}>open drawer</button>
            <Drawer activeSwitch={[drawerSwitch, setDrawerSwitch]}></Drawer>
        </>
    );
};
export default IndexPage;
