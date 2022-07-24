import { css } from '@emotion/react';
import { useEffect, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';
import { animated, config, useTransition } from 'react-spring';

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
    const [drawerMountState, setDrawerMountState] = useState(false);
    const transitions = useTransition(drawerActive, {
        from: { right: `${-drawerWidth}px` },
        enter: () => {
            setDrawerMountState(true);
            return { right: '0px' };
        },
        leave: () => {
            setTimeout(() => setDrawerMountState(false), 1050);
            return { right: `${-drawerWidth}px` };
        },
        // reverse: drawerActive,
        config: { duration: 1000 },
        // onStart: () => setDrawerMountState(!drawerMountState),
        // onRest: () => setDrawerMountState(false),
        // config: config.molasses,
        // onRest: () => setDrawerMountState(!drawerMountState),
    });

    /* -----------------Styles------------------ */
    const drawerWrapperStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            bottom: 0;
            overflow: hidden;
            background-color: skyblue;
            width: 100%;
            /* transition: ${drawerActive
                ? 'unset'
                : `width linear 0s ${transitionDuration + 0.05}s`}; */
        `,
        []
        /* drawerActive */
    );
    // eslint-disable-next-line prettier/prettier

    const drawerStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            bottom: 0;
            /* right: ${drawerActive ? 0 : -drawerWidth}px; */
            z-index: 1000;
            width: ${drawerWidth}px;
            background-color: #fff;
            /* transition: all linear ${transitionDuration}s 0.1s; */
        `,
        [drawerActive]
    );

    useEffect(() => {
        // if (!keepMounted) {
        //     if (drawerActive) setDrawerMountState(true);
        //     else {
        //         setTimeout(
        //             () => setDrawerMountState(false),
        //             (transitionDuration + 0.08) * 1000
        //         );
        //     }
        // }
        !drawerActive && onClose && onClose();
        // return () => {
        //     second;
        // };
    }, [drawerActive]);

    return createPortal(
        drawerMountState ? (
            <div className='drawer-wrapper' css={drawerWrapperStyle}>
                {withMask && (
                    <Mask
                        onClick={() => {
                            clickMaskToClose && setDrawerActive(false);
                        }}
                    />
                )}
                {transitions(
                    (styles, item) =>
                        item && (
                            <animated.div
                                className='drawer'
                                css={drawerStyle}
                                style={styles}>
                                <div
                                    className='drawer-children'
                                    style={{ width: '100%' }}>
                                    666666
                                    {children}
                                </div>
                            </animated.div>
                        )
                )}
            </div>
        ) : null,
        document.body
    );
};
export default Drawer;
