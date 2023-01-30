import { css } from '@emotion/react';

import NavBar from '../common/NavBar/NavBar';
import PasswordSetting from './parts/PasswordSetting';
import PaymentSetting from './parts/PaymentSetting';
import UserProfile from './parts/UserProfile';

const IndexPage = () => {
    const indexPageStyle = css`
        /* box-sizing: content-box; */
        > .content {
            margin: 0 auto;
            width: 700px;

            > * {
                margin-bottom: 40px;

                &:first-of-type {
                    margin-top: 20px;
                }

                &:last-of-type {
                    margin-bottom: 20px;
                }
            }
        }
    `;

    return (
        <div css={indexPageStyle}>
            <div className='navbar'>
                <NavBar />
            </div>
            <div className='content'>
                <UserProfile />

                <PasswordSetting />

                <PaymentSetting />
            </div>
        </div>
    );
};

export default IndexPage;
