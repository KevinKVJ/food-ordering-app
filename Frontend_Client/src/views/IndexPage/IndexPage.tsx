import { css } from '@emotion/react';
import { useState } from 'react';

import Button from '@/components/Buttons/Button';
import Flex from '@/components/FlexLayout/FlexLayout';
import Radio from '@/components/Radio/Radio';
import RadioGroup from '@/components/Radio/RadioGroup';
import Footer from '@/views/common/Footer/Footer';
import NavBar from '@/views/common/NavBar/NavBar';

// import { useMemo } from 'react';
import MainContent from './MainContent/MainContent';

const IndexPage = () => {
    const indexPageWrapperStyle = css`
        min-height: 100vh;
    `;

    const mainContentWrapperStyle = css`
        min-height: calc(100vh - 70px - 71px);
    `;

    const footerWrapperStyle = css`
        min-height: 70px;
        margin-top: 20px;
        /* border: 1px solid; */
    `;

    const [radioState, setRadioState] = useState('aaa');

    return (
        <div className='index-page-wrapper' css={indexPageWrapperStyle}>
            <div className='navbar-wrapper'>
                <NavBar />
            </div>
            <div className='main-content-wrapper' css={mainContentWrapperStyle}>
                <MainContent />
            </div>
            <RadioGroup
                value={radioState}
                name='test'
                onChange={e => setRadioState(e.target.value)}
            >
                <Radio value='aaa' label='AAA' />
                <Radio value='bbb' label='BBB' />
            </RadioGroup>
            {`RadioState : ${radioState}`}
            <br />
            <Flex>
                <Button />
                <Button />
                <Button />
            </Flex>
            <div className='footer-wrapper' css={footerWrapperStyle}>
                <Footer />
            </div>
        </div>
    );
};
export default IndexPage;
