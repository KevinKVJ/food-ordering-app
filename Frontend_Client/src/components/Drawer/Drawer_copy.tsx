import { css } from '@emotion/react';
import { useEffect, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';
import { animated, useSpring, useTransition } from 'react-spring';

import Mask from '@/components/Mask/Mask';

import type { drawerProps } from './DrawerType';

const Drawer = ({
    children,
    activeSwitch,
    drawerWidth = 200,
    transitionDuration = 0.25,
    withMask = true,
    clickMaskToClose = true,
    onClose,
}: // keepMounted = false,
drawerProps) => {
    /* -----------------States------------------ */
    const [drawerActive, setDrawerActive] = activeSwitch;
    // const [drawerMountState, setDrawerMountState] = useState(false);
    const drawerWrapperTransitions = useTransition(drawerActive, {
        // from: { opacity: '0' },
        enter: {
            from: { opacity: '0' },
            to: { opacity: '1' },
            config: { duration: 0 },
            delay: 50,
        },
        leave: {
            from: { opacity: '1' },
            to: { opacity: '0' },
            config: { duration: 0 },
            delay: 400,
        },
    });

    const drawerTransitions = useTransition(drawerActive, {
        from: { right: `${-drawerWidth}px` },
        enter: { right: 0 },
        leave: { right: `${-drawerWidth}px` },
        config: { duration: transitionDuration },
        expires: false,
    });
    /* -----------------Styles------------------ */
    const drawerWrapperStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            /* width: ${drawerActive ? '100%' : 0}; */
            width: 100%;
            bottom: 0;
            overflow: hidden;
            /* transition: ${drawerActive
                ? 'unset'
                : `width linear 0s ${transitionDuration + 0.05}s`}; */
        `,
        []
    );

    const drawerStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            bottom: 0;
            /* right: ${drawerActive ? 0 : -drawerWidth}px; */
            z-index: 1000;
            width: ${drawerWidth}px;
            background-color: #fff;
            /* transition: right linear ${transitionDuration}s 0.1s; */
        `,
        []
    );

    useEffect(() => {
        !drawerActive && onClose && onClose();
    }, [drawerActive]);

    // useEffect(() => {

    //     //   return () => {
    //     //     second
    //     //   }
    // }, [keepMounted]);

    return createPortal(
        drawerWrapperTransitions(
            (wrapperTransitionstyle, ifWrapperMount) =>
                ifWrapperMount && (
                    <animated.div
                        className='drawer-wrapper'
                        css={drawerWrapperStyle}
                        style={wrapperTransitionstyle}>
                        {withMask && (
                            <Mask
                                onClick={() => {
                                    clickMaskToClose &&
                                        setDrawerActive(false);
                                }}
                            />
                        )}
                        {drawerTransitions(
                            (styles, item) =>
                                item && (
                                    <animated.div
                                        className='drawer'
                                        css={drawerStyle}
                                        style={styles}>
                                        <div
                                            className='drawer-children'
                                            style={{
                                                width: '100%',
                                            }}>
                                            666666
                                            {children}
                                        </div>
                                    </animated.div>
                                )
                        )}
                    </animated.div>
                )
        ),
        document.body
    );
};
export default Drawer;
