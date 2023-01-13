import { css } from '@emotion/react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import InputWithTitle from './parts/InputWithTitle';

const Login = () => {
    const navigate = useNavigate();
    const [phone, setPhone] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const dontHaveAccountPrompt = "Don't have an account? ";
    /* background: ${props =>
                `rgba(0,0,0,0.7) url(${props.bkg}) no-repeat center / cover`}; */
    const loginCss = css`
        width: 100%;
        min-height: 100vh;

        position: relative;
        z-index: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        overflow-y: hidden;
        &::before {
            content: '';
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            z-index: -1;
            opacity: 0.4;
        }
        > .login-window {
            border: 1px solid;

            width: 380px;
            height: 582px;
            padding: 40px 32px;
            background-color: #fff;
            display: flex;
            flex-direction: column;
            align-items: center;
            /* justify-content: center; */
            > .logo {
                width: 130px;
                height: 80px;
                background-color: skyblue;
            }
            > .titles {
                div {
                    text-align: center;
                    letter-spacing: 0.25px;
                }
                .title {
                    font-size: 24px;
                    font-weight: 600;
                    margin-bottom: 12px;
                }
                .sub-title {
                    font-size: 14px;
                    color: #9fa2b4;
                }
            }
            > .titles {
                margin-top: 32px;
                margin-bottom: 48px;
            }
            > .inputs {
                width: 100%;
                > div {
                    width: 100%;
                    margin-bottom: 24px;
                    > .title {
                        font-weight: 700;
                        font-size: 12px;
                        line-height: 15px;
                        letter-spacing: 0.3px;
                        color: #9fa2b4;
                        margin-bottom: 6px;
                    }
                    > .input {
                        width: 100%;
                        input {
                            width: 100%;
                            /* height: 42px; */
                            padding: 11px 8px 11px 16px;
                            border: 1px solid #f0f1f7;
                            box-sizing: border-box;
                            border-radius: 8px;
                            &::placeholder {
                                font-size: 14px;
                                line-height: 20px;
                                letter-spacing: 0.3px;
                                color: #4b506d;
                                opacity: 0.4;
                            }
                        }
                    }
                }
            }
            .confirm-btn {
                margin-bottom: 32px;
                background: #3751ff;
                box-shadow: 0px 4px 12px rgba(55, 81, 255, 0.24);
                border-radius: 8px;
                width: 316px;
                height: 48px;
                text-align: center;
                padding: 15px 0;
                font-weight: 400;
                font-size: 14px;
                line-height: 20px;
                letter-spacing: 0.2px;
                color: #ffffff;
                cursor: pointer;
            }
            .signup-prompt {
                font-weight: 400;
                font-size: 14px;
                line-height: 20px;
                text-align: center;
                letter-spacing: 0.3px;
                .signup {
                    color: #3751ff;
                    cursor: pointer;
                }
                color: #9fa2b4;
            }
        }
        input:focus,
        textarea:focus {
            outline: none;
            border: 1px solid #f60;
        }
    `;

    return (
        <>
            <div className='container' css={loginCss}>
                <div className='login-window'>
                    <div className='logo'></div>
                    <div className='titles'>
                        <div className='title'>Log In to XXX</div>
                        <div className='sub-title'>
                            Enter your email and password below
                        </div>
                    </div>
                    <div className='inputs'>
                        <InputWithTitle
                            className='phone'
                            title='PHONE'
                            inputPlaceholder='Phone number'
                            value={phone}
                            onChange={e => setPhone(e.target.value)}
                        />
                        <InputWithTitle
                            className='password'
                            title='PASSWORD'
                            inputPlaceholder='Password'
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </div>
                    {/* <div className='confirm-btn' onClick={() => loginRequest()}> */}
                    <div className='confirm-btn'>Log In</div>
                    <div className='signup-prompt'>
                        <span className='prompt'>{dontHaveAccountPrompt}</span>
                        <span className='signup' onClick={() => navigate('/signup')}>
                            Sign up
                        </span>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Login;
