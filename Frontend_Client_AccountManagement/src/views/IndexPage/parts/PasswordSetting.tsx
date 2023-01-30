import { css } from '@emotion/react';

import AccountSettingBlock from './AccountSettingBlock';

const PasswordSettingStyle = css`
    .password_setting_content {
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

const PasswordSetting = ({ className }: { className?: string }) => {
    return (
        <div css={PasswordSettingStyle} className={className}>
            <AccountSettingBlock title='Password Setting'>
                <div className='password_setting_content'>
                    <div className='ori_pwd'>
                        <div className='subtitle'>Original Password</div>
                        <div className='ori_password_inputs'>
                            <input
                                type='text'
                                name=''
                                id=''
                                placeholder='Original password'
                            />
                        </div>
                    </div>
                    <div className='new_pwd'>
                        <div className='subtitle'>New Password</div>
                        <div className='new_password_inputs'>
                            <input type='text' name='' id='' placeholder='New password' />
                        </div>
                    </div>
                    <div className='cfm_new_pwd'>
                        <div className='subtitle'>Confirm New Password</div>
                        <div className='cfm_password_inputs'>
                            <input
                                type='text'
                                name=''
                                id=''
                                placeholder='Confirm password'
                            />
                        </div>
                    </div>
                </div>
            </AccountSettingBlock>
        </div>
    );
};

export default PasswordSetting;
