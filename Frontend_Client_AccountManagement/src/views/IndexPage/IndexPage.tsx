import { css } from '@emotion/react';

import NavBar from '../common/NavBar/NavBar';

const IndexPage = () => {
    const indexPageStyle = css`
        .content {
            margin: 0 auto;
            width: 700px;
        }

        .block {
            padding: 35px;

            > .main_title {
                font-weight: 700;
                font-size: 24px;
            }
        }
    `;

    return (
        <div css={indexPageStyle}>
            <div className='navbar'>
                <NavBar />
            </div>
            <div className='content'>
                <div className='user_profile block'>
                    <div className='main_title'>User Profile</div>
                    <div className='user_profile_content'>
                        <div className='full_name'>
                            <div className='subtitle'>Full Name</div>
                        </div>
                        <div className='email'>
                            <div className='subtitle'>Email</div>
                        </div>
                        <div className='phone_number'>
                            <div className='subtitle'>
                                Phone Number <span>Required</span>
                            </div>
                        </div>
                        <div className='country'>
                            <div className='subtitle'>Country</div>
                        </div>
                    </div>
                </div>
                <div className='password_setting block'>
                    <div className='main_title'>Password Setting</div>
                    <div className='password_setting_content'>
                        <div className='ori_pwd'>
                            <div className='subtitle'>Original Password</div>
                        </div>
                        <div className='new_pwd'>
                            <div className='subtitle'>New Password</div>
                        </div>
                        <div className='cfm_new_pwd'>
                            <div className='subtitle'>Confirm New Password</div>
                        </div>
                    </div>
                </div>
                <div className='payment block'>
                    <div className='main_title'>Payment</div>
                    <div className='payment_content'>
                        <div className='methods'>
                            <div className='saved'>
                                <div className='subtitle smaller'>
                                    Saved Payment Method
                                </div>
                            </div>
                            <div className='add_new'>
                                <div className='subtitle smaller'>
                                    Add New Payment Method
                                </div>
                            </div>
                        </div>
                        <div className='cash'>
                            <div className='subtitle larger'>Cash</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default IndexPage;
