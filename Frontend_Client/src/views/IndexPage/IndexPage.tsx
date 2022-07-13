import NavBar from '@/views/common/NavBar/NavBar';
import MainContent from './MainContent/MainContent'
import Footer from '@/views/common/Footer/Footer';
export default () => {
    return (
        <>
            <div className='navbar'>
                <NavBar />
            </div>
            <div className='main-content'>
                <MainContent />
            </div>
            <div className='footer'>
                <Footer></Footer>
            </div>
        </>
    );
};
