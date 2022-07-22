import './drawerStyle.scss';

import { css } from '@emotion/react';
import { PropsWithChildren, useEffect, useMemo, useRef, useState } from 'react';
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
    // const [variableWrapperWidth, setVariableWrapperWidth] = useState<string>('100%');

    // const drawerWrapperStyle = useMemo(
    //     () => css`
    //         position: absolute;
    //         top: 0;
    //         /* width: 0; */
    //         bottom: 0;
    //         overflow: hidden;
    //     `,
    //     []
    // );
    // const drawerStyle = useMemo(
    //     () => css`
    //         position: absolute;
    //         top: 0;
    //         bottom: 0;
    //         right: ${-drawerWidth}px;
    //         /* right: ${drawerActive ? 0 : -drawerWidth}px; */
    //         z-index: 1000;
    //         width: ${drawerWidth}px;
    //         background-color: #fff;
    //     `,
    //     [drawerActive]
    // );
    // useEffect(() => {
    //     setDrawerActive(true);
    // }, []);
    // useEffect(() => {
    //     drawerActive ? setVariableWrapperWidth('100%') : setTimeout(() => setVariableWrapperWidth('0'), 250);
    //     // console.log(drawerActive);
    // }, [drawerActive]);

    /* css={drawerWrapperStyle} */
    const transitionComp = css`
        .ccc-enter {
            // .drawer-wrapper {
            //     width: 100%;
            //     position: absolute;
            //     top: 0;
            //     bottom: 0;
            //     overflow: hidden;
            // }

            .drawer {
                // position: absolute;
                // top: 0;
                // bottom: 0;
                // z-index: 1000;
                // width: 500px;
                // background-color: #fff;
                right: -500px;
            }
        }

        .ccc-enter-active {
            .drawer {
                // position: absolute;
                // top: 0;
                // bottom: 0;
                right: 0px;
                // z-index: 1000;
                // width: 500px;
                // background-color: #fff;
                transition: right linear 0.25s;
            }
        }
    `;

    const nodeRef = useRef(null);

    /* css={drawerWrapperStyle} css={drawerStyle}*/
    return createPortal(
        <CSSTransition nodeRef={nodeRef} classNames='ccc' unmountOnExit in={drawerActive} timeout={250} css={transitionComp}>
            <div ref={nodeRef} className='drawer-wrapper'>
                <Mask
                    onClick={() => {
                        setDrawerActive(false);
                    }}
                />

                <div className='drawer'>666666</div>
            </div>
        </CSSTransition>,
        document.body
    );
};
export default Drawer;
