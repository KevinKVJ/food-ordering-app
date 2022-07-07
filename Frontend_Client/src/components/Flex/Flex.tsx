import { useMemo } from 'react';
import type { CSSProperties, PropsWithChildren, FC } from 'react';
import React from 'react';
import styles from './Flex.module.scss';
import useParseStyles from '@/utils/parseStyles';
import { useClassNameStr } from './customHooks';

interface FlexProps extends PropsWithChildren {
    spacing?: 'small' | 'medium' | 'large' | number | [number, number];
    itemStyle?: string | CSSProperties;
    vertical?: boolean;
    wrap?: boolean;
}

const Flex: FC<FlexProps> = ({ children, spacing = 'medium', itemStyle }) => {
    const flexSpacing = useMemo(() => {
        if (spacing instanceof Number) return String(spacing);
        else if (spacing instanceof Array)
            return spacing.map(val => `${val}px`).join(' ');
        else return spacing;
    }, [spacing]);

    const childStyle = useMemo(
        () =>
            typeof itemStyle === 'string'
                ? useParseStyles(itemStyle)
                : itemStyle,
        [itemStyle]
    );

    const wrapperClass: string = useMemo(
        () =>
            useClassNameStr([
                styles.flexBox,
                flexSpacing === 'small' ||
                flexSpacing === 'medium' ||
                flexSpacing === 'large'
                    ? styles[`mg-${flexSpacing}`]
                    : '',
            ]),
        [flexSpacing, styles.flexBox]
    );

    const wrapperStyle: CSSProperties = useMemo(
        () => ({
            gap:
                flexSpacing === 'small' ||
                flexSpacing === 'medium' ||
                flexSpacing === 'large'
                    ? undefined
                    : flexSpacing,
        }),
        [flexSpacing]
    );

    return (
        <>
            {/* {console.log(flexSpacing, styles[`mg-${flexSpacing}`])} */}
            <div className={wrapperClass} style={wrapperStyle}>
                {React.Children.toArray(children).map(
                    (child, index, oriChilds) => {
                        return (
                            <div
                                key={index}
                                className={`666`}
                                style={childStyle}>
                                {child}
                            </div>
                        );
                    }
                )}
            </div>
        </>
    );
};

export default Flex;