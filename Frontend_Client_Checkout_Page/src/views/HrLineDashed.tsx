import { css } from '@emotion/react';

const HrLineDashed = () => {
    const HrLineDashedStyle = css`
        border-bottom: 1px dashed #dbdbdb;
        margin: 15px 0;
    `;
    return <div className='hrline_dashed' css={HrLineDashedStyle}></div>;
};

export default HrLineDashed;
