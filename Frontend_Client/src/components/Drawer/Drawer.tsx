import { css } from '@emotion/react';
import type { Dispatch, SetStateAction } from 'react';
import { PropsWithChildren, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';

import Mask from '@/components/Mask/Mask';

type activeSwitchUseStateType =
    | ReturnType<typeof useState<boolean>>
    | [boolean, ((arg0: boolean) => void) | Dispatch<SetStateAction<boolean>>];
// eslint-disable-next-line prettier/prettier

interface drawerProps extends PropsWithChildren {
    activeSwitch: activeSwitchUseStateType;
    drawerWidth?: number;
    onClose?: () => void;
    transitionDuration?: number;
    withMask?: boolean;
    clickMaskToClose?: boolean;
}

const Drawer = ({
    children,
    activeSwitch,
    drawerWidth = 500,
    transitionDuration = 0.5,
    withMask = true,
    clickMaskToClose = true,
}: drawerProps) => {
    /* -----------------States------------------ */
    const [drawerActive, setDrawerActive] = activeSwitch;

    /* -----------------Styles------------------ */
    const drawerWrapperStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            width: ${drawerActive ? '100%' : 0};
            bottom: 0;
            overflow: hidden;
            transition: ${drawerActive ? 'unset' : `width linear 0s ${transitionDuration + 0.05}s`};
        `,
        [drawerActive]
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
            transition: right linear ${transitionDuration}s;
        `,
        [drawerActive]
    );

    // useEffect(() => {
    //     setDrawerActive(activeSwitch);

    //     // return () => {
    //     //     second;
    //     // };
    // }, [activeSwitch]);

    return createPortal(
        <div className='drawer-wrapper' css={drawerWrapperStyle}>
            {withMask && drawerActive && (
                <Mask
                    onClick={() => {
                        clickMaskToClose && setDrawerActive(false);
                    }}
                />
            )}
            <div className='drawer' css={drawerStyle}>
                <div className='drawer-children' style={{ width: '100%' }}>
                    666666
                    {children}
                </div>
            </div>
        </div>,
        document.body
    );
};
export default Drawer;
