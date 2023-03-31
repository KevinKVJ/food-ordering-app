import { css } from '@emotion/react';

const HrLine = () => {
    const HrLineStyle = css`
        border-bottom: 2px solid #c4c4c4;
        margin: 15px 0;
    `;
    return <div className='hrline' css={HrLineStyle}></div>;
};

export default HrLine;
