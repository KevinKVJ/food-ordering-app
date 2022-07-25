import { css } from '@emotion/react';
import { Dispatch, SetStateAction, useEffect } from 'react';
import { PropsWithChildren, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';

import Mask from '@/components/Mask/Mask';

type activeSwitchUseStateType =
    | ReturnType<typeof useState<boolean>>
    | [
          boolean,
          ((arg0: boolean) => void) | Dispatch<SetStateAction<boolean>>
      ];
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
    activeSwitch: MountSwitch, // alias name
    drawerWidth = 500,
    transitionDuration = 0.5,
    withMask = true,
    clickMaskToClose = true,
    onClose,
}: drawerProps) => {
    /* -----------------States------------------ */
    const [drawerMount, setDrawerMount] = MountSwitch;
    const [drawerInternalActiveState, setDrawerInternalActiveState] =
        useState(false);

    /* -----------------Styles------------------ */
    const drawerWrapperStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            width: ${drawerInternalActiveState ? '100%' : 0};
            bottom: 0;
            overflow: hidden;
            transition: ${drawerInternalActiveState
                ? 'unset'
                : `width linear 0s ${transitionDuration + 0.05}s`};
        `,
        [drawerInternalActiveState]
    );
    const drawerStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            bottom: 0;
            right: ${drawerInternalActiveState ? 0 : -drawerWidth}px;
            z-index: 1000;
            width: ${drawerWidth}px;
            background-color: #fff;
            transition: right linear ${transitionDuration}s;
        `,
        [drawerInternalActiveState]
    );

    useEffect(() => {
        drawerMount && setDrawerInternalActiveState(true);
        if (!drawerMount) {
            !!onClose && onClose();
        }

        // return () => {
        //     console.log('onClose执行');
        // };
    }, [drawerMount]);

    // useEffect(() => {
    //     return () => console.log('onClose执行111111');
    // }, []);

    useEffect(() => {
        !drawerInternalActiveState &&
            setTimeout(
                () => setDrawerMount(false),
                transitionDuration * 1000 + 10
            );
    }, [drawerInternalActiveState]);

    // const handleClose = () => {
    //     if (clickMaskToClose) {
    //         setDrawerMountState(false);
    //         setTimeout(
    //             () => setDrawerActive(false),
    //             transitionDuration * 1000 + 10
    //         );
    //     }
    // };

    return createPortal(
        drawerMount && (
            <div
                className='drawer-wrapper'
                css={drawerWrapperStyle}
                // onTransitionEnd={() =>
                //     !drawerActive && setDrawerMountState(false)
                // }
            >
                {withMask && drawerInternalActiveState && (
                    <Mask
                        onClick={() =>
                            clickMaskToClose &&
                            setDrawerInternalActiveState(false)
                        }
                    />
                )}
                <div className='drawer' css={drawerStyle}>
                    <div
                        className='drawer-children'
                        style={{ width: '100%' }}>
                        666666
                        {children}
                    </div>
                </div>
            </div>
        ),
        document.body
    );
};
export default Drawer;
