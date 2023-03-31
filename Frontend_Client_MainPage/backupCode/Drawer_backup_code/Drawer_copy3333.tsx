import { css } from '@emotion/react';
import { useEffect, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';

import Mask from '@/components/Mask/Mask';

import type { drawerProps } from './DrawerType';

const DrawerInternal = ({
    children,
    activeSwitch: MountSwitch,
    drawerWidth = 300,
    transitionDuration = 0.25,
    withMask = true,
    clickMaskToClose = true,
    onClose,
}: drawerProps) => {
    /* -----------------States------------------ */
    // const [, setDrawerMount] = MountSwitch;
    const [drawerInternalActiveState, setDrawerInternalActiveState] =
        useState(false);

    /* -----------------Styles------------------ */
    const drawerWrapperStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            width: 100%;
            bottom: 0;
            overflow: hidden;
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
        setDrawerInternalActiveState(true);
        return () => {
            !!onClose && onClose();
        };
    }, []);

    const transitionEnd = () => {
        !drawerInternalActiveState && setDrawerMount(false);
    };

    return createPortal(
        <div className='drawer-wrapper' css={drawerWrapperStyle}>
            {withMask && drawerInternalActiveState && (
                <Mask
                    onClick={() =>
                        clickMaskToClose &&
                        setDrawerInternalActiveState(false)
                    }
                />
            )}
            <div
                className='drawer'
                css={drawerStyle}
                onTransitionEnd={transitionEnd}>
                <div className='drawer-children' style={{ width: '100%' }}>
                    {children}
                </div>
            </div>
        </div>,
        document.body
    );
};

const Drawer = ({ activeSwitch: MountSwitch, ...props }: drawerProps) => {
    const [drawerMount] = MountSwitch;

    return drawerMount ? (
        <DrawerInternal activeSwitch={MountSwitch} {...props} />
    ) : null;
};

export default Drawer;
