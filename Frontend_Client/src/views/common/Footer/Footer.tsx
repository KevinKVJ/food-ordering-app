import { css } from '@emotion/react';
import { useMemo } from 'react';

const Footer = () => {
    const footerStyles = useMemo(
        () => css`
            text-align: center;
        `,
        []
    );
    return <div css={footerStyles}>Food Ordering System</div>;
};
export default Footer;
