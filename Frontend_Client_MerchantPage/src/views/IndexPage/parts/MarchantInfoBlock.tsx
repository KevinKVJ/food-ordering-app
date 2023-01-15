/* eslint-disable jsx-a11y/anchor-has-content */
import { css } from '@emotion/react';

const MerchantInfoBlock = ({
    className,
    title,
    desc,
    info,
    starRating,
    merchantLink,
}: {
    className?: string;
    title?: string;
    desc?: string;
    info?: string;
    starRating?: string;
    merchantLink?: string;
}) => {
    const MerchantInfoBlockStyle = css`
        position: relative;

        width: 300px;
        /* outline: 1px solid; */
        .foodImg {
            height: 200px;
            background-color: #ffa600;
        }

        .info_container {
            padding: 10px;

            .desc,
            .title,
            .info,
            .star_rating {
                margin-bottom: 3px;
                white-space: pre-wrap;
            }

            .desc,
            .star_rating {
                font-size: 14px;
                color: #cbcbcb;
            }

            .info {
                font-size: 15px;
            }

            .title {
                font-size: 20px;
                font-weight: 600;
            }
        }
        .link_mask {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;

            cursor: pointer;
        }
    `;

    return (
        <div className={className} css={MerchantInfoBlockStyle}>
            <a href={merchantLink} className='link_mask' />
            <div className='foodImg'></div>
            <div className='info_container'>
                <div className='desc'>{desc}</div>
                <div className='title'>{title}</div>
                <div className='info'>{info}</div>
                <div className='star_rating'>{starRating}</div>
            </div>
        </div>
    );
};

export default MerchantInfoBlock;
