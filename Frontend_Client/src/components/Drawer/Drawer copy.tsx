import { css } from '@emotion/react';
import { PropsWithChildren, useEffect, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';
import { CSSTransition } from 'react-transition-group';

import Mask from '@/components/Mask/Mask';

interface drawerProps extends PropsWithChildren {
    activeSwitch?: boolean;
    drawerWidth?: number;
    onClose?: () => void;
}

const Drawer = ({ activeSwitch = true, drawerWidth = 500 }: drawerProps) => {
    const [drawerActive, setDrawerActive] = useState<boolean>(activeSwitch);
    const [variableWrapperWidth, setVariableWrapperWidth] = useState<string>('100%');

    const drawerWrapperStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            width: ${variableWrapperWidth};
            bottom: 0;
            overflow: hidden;
        `,
        [variableWrapperWidth]
    );
    const drawerStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            bottom: 0;
            right: ${drawerActive ? 0 : -drawerWidth}px;
            z-index: 1000;
            width: ${drawerWidth}px;
            background-color: #fff;
            transition: all linear 1.2s;
        `,
        [drawerActive]
    );
    useEffect(() => {
        drawerActive ? setVariableWrapperWidth('100%') : setTimeout(() => setVariableWrapperWidth('0'), 250);
        // console.log(drawerActive);
    }, [drawerActive]);

    /* css={drawerWrapperStyle} */
    return createPortal(
        <div className='drawer-wrapper' css={drawerWrapperStyle} onTransitionEnd>
            {drawerActive && (
                <Mask
                    onClick={() => {
                        setDrawerActive(false);
                    }}
                />
            )}
            <div className='drawer' css={drawerStyle}>
                666666
            </div>
        </div>,
        document.body
    );
};
export default Drawer;
