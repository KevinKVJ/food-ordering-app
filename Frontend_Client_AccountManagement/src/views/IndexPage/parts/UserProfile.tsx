import { css } from '@emotion/react';

import Flex from '@/components/FlexLayout/FlexLayout';

import AccountSettingBlock from './AccountSettingBlock';

const userProfileStyle = css`
    .profile_setting_content {
        .subtitle {
            font-size: 14px;
            font-weight: 600;

            &.larger {
                font-size: 18px;
            }
            &.smaller {
                font-size: 13px;
            }
        }
    }
`;

const UserProfile = ({ className }: { className?: string }) => {
    return (
        <div css={userProfileStyle} className={className}>
            <AccountSettingBlock title='User Profile'>
                <div className='profile_setting_content'>
                    <div className='full_name'>
                        <div className='subtitle'>Full Name</div>
                        <Flex
                            className='full_name_inputs'
                            justifyContent='space-between'
                            spacing='unset'
                        >
                            <div className='first_name'>
                                <input
                                    type='text'
                                    name=''
                                    id=''
                                    placeholder='First name'
                                />
                            </div>
                            <div className='last_name'>
                                <input
                                    type='text'
                                    name=''
                                    id=''
                                    placeholder='Last name'
                                />
                            </div>
                        </Flex>
                    </div>
                    <div className='email'>
                        <div className='subtitle'>Email</div>
                        <div className='email_inputs'>
                            <input type='text' name='' id='' placeholder='Email' />
                        </div>
                    </div>
                    <div className='phone_number'>
                        <div className='subtitle'>
                            Phone Number <span>Required</span>
                        </div>
                        <div className='p_number_inputs'>
                            <input type='text' name='' id='' placeholder='Phone Number' />
                        </div>
                    </div>
                    <div className='country'>
                        <div className='subtitle'>Country</div>
                        <div className='country_inputs'>
                            <input type='text' name='' id='' placeholder='Country' />
                        </div>
                    </div>
                </div>
            </AccountSettingBlock>
        </div>
    );
};

export default UserProfile;
