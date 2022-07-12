import { css } from '@emotion/react';
import SvgIcon from '@/components/SvgIcon';
import Flex from '@/components/FlexLayout/FlexLayout';

const navStyles1 = css({
    height: '70px',
    display: 'flex',
    alignItems: 'center',
});

const navStyles2 = ()=> css`
    background-color: red;
`;

export default () => {
    return (
        <div css={[navStyles1,navStyles2]}>
            <Flex>
                <SvgIcon prefix='icon' name='menu' width={30} height={30} />
                <SvgIcon prefix='icon' name='menu' width={30} height={30} />
                <SvgIcon prefix='icon' name='menu' width={30} height={30} />
                <SvgIcon prefix='icon' name='menu' width={30} height={30} />
            </Flex>
        </div>
    );
};
