<template>
    <div class="block-container">
        <div className="login-window">
            <div class="logo"></div>
            <div class="titles">
                <div class="title">Sign Up To the FOA</div>
                <div class="sub-title">Enter your email and password below</div>
            </div>
            <div class="inputs">
                <div class="email">
                    <div class="title">PHONE</div>
                    <div class="input">
                        <input type="text" placeholder="Phone number" v-model="phoneValue"/>
                    </div>
                </div>
                <div class="password">
                    <div class="title">PASSWORD</div>
                    <div class="input">
                        <input type="password" placeholder="Password" v-model="passwdValue"/>
                    </div>
                </div>
            </div>
            <div class="confirm-btn">Comfirm</div>
            <div class="signup-prompt">
                <span class="propmt">Already have an account? </span>
                <span class="signup" @click="router.push('/login')" >Sign In</span>
            </div>
        </div>
    </div>
</template>

<script setup lang="tsx">
import { ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'

const router = useRouter();

const phoneValue = ref<string>('');
const passwdValue = ref<string>('');
</script>

<style lang="scss" scoped>
.block-container {
    width: 100%;
    height: 100%;
    position: relative;
    z-index: 0;

    display: flex;
    align-items: center;
    justify-content: center;
    overflow-y: hidden;

    &::before {
        content: '';
        background: deepskyblue;
        position: absolute;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        z-index: -1;
        opacity: 0.4;
    }

    > .login-window {
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

            display: inline-block;

            /* width: 316px; */
            /* height: 48px; */
            text-align: center;
            padding: 15px 150px;

            font-weight: 600;
            font-size: 18px;
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
                padding-bottom: 1px;
                border-bottom: 1px solid;
            }
            color: #9fa2b4;
        }
    }

    input:focus,
    textarea:focus {
        outline: none;

        border: 1px solid #f60;
    }
}
</style>
