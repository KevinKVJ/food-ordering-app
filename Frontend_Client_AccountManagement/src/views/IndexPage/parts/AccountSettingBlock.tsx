import { css } from '@emotion/react';
import { PropsWithChildren } from 'react';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface AccountSettingBlockProps extends PropsWithChildren {
    title: string;
}

const AccountSettingBlockStyle = css`
    /* width: 100%; */
    padding: 35px;
    border: 1px solid #e7e7e7;

    > .asb_main_title {
        margin-bottom: 20px;

        font-weight: 700;
        font-size: 24px;
    }
`;

const AccountSettingBlock = ({ children, title }: AccountSettingBlockProps) => {
    return (
        <div className='asb_wrapper' css={AccountSettingBlockStyle}>
            <div className='asb_main_title'>{title}</div>
            <div className='asb_content'>{children}</div>
        </div>
    );
};

export default AccountSettingBlock;
