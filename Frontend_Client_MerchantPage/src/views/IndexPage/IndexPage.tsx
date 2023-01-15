import { css } from '@emotion/react';
import { useState } from 'react';

import NavBar from '../common/NavBar/NavBar';
import MerchantInfoBlock from './parts/MarchantInfoBlock';

const IndexPage = ({ className }: { className?: string }) => {
    // setMainTitle setMainDesc
    const [mainTitle] = useState('Tag Title');
    const [mainDesc] = useState('(Description...)');

    const indexPageStyle = css`
        background-color: #f5f3f1;

        width: 100%;

        .navbar {
            margin-bottom: 1px;

            background-color: #fff;
        }

        .content_wrapper {
            /* outline: 1px solid; */
            background-color: #fff;
            width: 1000px;
            min-height: calc(100vh - 72px);
            padding: 30px 24px;
            margin: 0 auto;
        }

        .title_desc {
            padding: 0 10px;
            margin-bottom: 20px;

            .main_title {
                margin-bottom: 10px;

                font-size: 30px;
                font-weight: 700;
            }

            .main_desc {
                font-weight: 600;

                color: #767676;
            }
        }

        .content_block {
            margin-bottom: 40px;
        }

        .hr1 {
            margin: 10px 0;

            border-top: 3px solid #e7e7e7;
            color: #e7e7e7;
        }

        .sub_title {
            padding: 0 10px;
            font-weight: 700;
            font-size: 20px;
        }

        .marchant_content_block {
            white-space: pre;

            .mcb_grid {
                display: grid;
                grid-template-columns: repeat(3, auto);
                grid-column-gap: 20px;
                grid-row-gap: 20px;
                justify-items: stretch;
                align-items: stretch;
            }
        }
    `;
    return (
        <div css={indexPageStyle} className={className}>
            <div className='navbar'>
                <NavBar />
            </div>
            <div className='content_wrapper'>
                <div className='title_desc'>
                    <div className='main_title'>{mainTitle}</div>
                    <div className='main_desc'>{mainDesc}</div>
                </div>
                <div className='best_deals content_block'>
                    <div className='sub_title'>Your Best Deals</div>
                    <div className='hr1' />
                    <div className='marchant_content_block'>
                        <div className='mcb_grid'>
                            <MerchantInfoBlock
                                desc='xixixi xixixi    xixixi'
                                title='lalala'
                                info='xixixixi'
                                starRating='4.8'
                                merchantLink='https://www.google.com'
                            />
                        </div>
                    </div>
                </div>

                <div className='all_stores content_block'>
                    <div className='sub_title'>All Stores</div>
                    <div className='hr1' />
                    <div className='marchant_content_block'>
                        <div className='mcb_grid'>
                            <MerchantInfoBlock
                                title='lalala'
                                desc='xixixi xixixi    xixixi'
                            />
                            <MerchantInfoBlock
                                title='lalala'
                                desc='xixixi xixixi    xixixi'
                            />
                            <MerchantInfoBlock
                                title='lalala'
                                desc='xixixi xixixi    xixixi'
                            />
                            <MerchantInfoBlock
                                title='lalala'
                                desc='xixixi xixixi    xixixi'
                            />
                            <MerchantInfoBlock
                                title='lalala'
                                desc='xixixi xixixi    xixixi'
                            />
                            <MerchantInfoBlock
                                title='lalala'
                                desc='xixixi xixixi    xixixi'
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default IndexPage;
