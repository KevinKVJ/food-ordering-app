import Footer from '@/views/common/Footer/Footer';
import Mask from '@/views/common/Mask/Mask';
import NavBar from '@/views/common/NavBar/NavBar';

import MainContent from './MainContent/MainContent';
const IndexPage = () => {
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
            <Mask></Mask>
        </>
    );
};
export default IndexPage;
