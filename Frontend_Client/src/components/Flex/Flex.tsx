import { useMemo } from 'react';
import type { CSSProperties, PropsWithChildren, FC } from 'react';
import React from 'react';
import styles from './Flex.module.scss';
import { useClassNameStr, useFlexSpacings, useItemStyle } from './customHooks';

interface FlexProps extends PropsWithChildren {
    spacing?: 'small' | 'medium' | 'large' | number | [number, number];
    itemStyle?: string | CSSProperties;
    vertical?: boolean;
    wrap?: boolean;
}

const Flex: FC<FlexProps> = ({ children, spacing = 'medium', itemStyle,vertical,wrap }) => {
    const flexSpacing = useFlexSpacings(spacing);
    const childStyle = useItemStyle(itemStyle);

    const wrapperClass: string = useMemo(() => {
        return useClassNameStr([
            `flex`,
            vertical ? `flex-col` : '',
            wrap ? `flex-wrap` : '',
            // `gap-y-${flexSpacingY}px`,
        ]);
    }, [vertical,wrap]);

    const wrapperStyle: CSSProperties = useMemo(() => {
        const [flexSpacingX, flexSpacingY] = flexSpacing;
        return {
            gap: `${flexSpacingX}px ${flexSpacingY}px`,
        };
    }, [flexSpacing]);

    const childClass : string = useMemo(() => {
        return useClassNameStr([
            `flex-[0_0_auto]`
        ]);
    },[]);

    return (
        <>
            <div className={wrapperClass} style={wrapperStyle}>
                {React.Children.toArray(children).map(
                    (child, index, oriChilds) => {
                        return (
                            <div
                                key={index}
                                className={childClass}
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
