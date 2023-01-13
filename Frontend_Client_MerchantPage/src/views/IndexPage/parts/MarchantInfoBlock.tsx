import { css } from '@emotion/react';

const MerchantInfoBlock = ({
    className,
    title,
    desc,
    info,
    starRating,
}: {
    className?: string;
    title?: string;
    desc?: string;
    info?: string;
    starRating?: string;
}) => {
    const MerchantInfoBlockStyle = css`
        width: 300px;

        .foodImg {
            height: 100px;
        }

        .desc,
        .title,
        .info,
        .star_rating {
            white-space: pre-wrap;
        }
    `;

    return (
        <div className={className} css={MerchantInfoBlockStyle}>
            <div className='foodImg'></div>
            <div className='desc'>{desc}</div>
            <div className='title'>{title}</div>
            <div className='info'>{info}</div>
            <div className='star_rating'>{starRating}</div>
        </div>
    );
};

export default MerchantInfoBlock;
