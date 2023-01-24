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

            .payment_content {
                display: flex;

                .methods,
                .cash {
                    flex-grow: 1;
                    flex-basis: 50%;
                    flex-shrink: 1;
                    outline: 1px solid;
                }

                .split_vertical_line {
                    border-right: 2px solid;
                    margin: 0 20px;
                }

                .cash {
                    .cash_prompt {
                        color: #a9a9a9;
                        font-size: 12px;
                        word-break: pre;
                    }
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
