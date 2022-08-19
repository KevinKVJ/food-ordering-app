import { css } from '@emotion/react';
import { Children, FC, PropsWithChildren, useMemo } from 'react';

interface ICSwiperProps extends PropsWithChildren {
    showSlides?: number;
    initialSlide?: number;
}

const CategorySwiper: FC<ICSwiperProps> = ({
    showSlides = 10,
    initialSlide = 0,
    children,
}) => {
    const [prevValid, setPrevValid] = useState(true);
    const [nextValid, setNextValid] = useState(true);
    const swiper1Ref = useRef<SwiperRefTypes>(null);
    const [currentSlide, setCurrentSlide] = useState(initialSlide);
    const [currentPage, setCurrentPage] = useState(
        Math.floor(initialSlide / (showSlides as number) + 1)
    );

    const swiperButton = useMemo(
        () => css`
            width: 34px;
            height: 34px;
            border: 1px solid #e9e9ea;
            border-radius: 1000px;

            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;

            cursor: pointer;
            user-select: none;
        `,
        []
    );

    const swiperButtonBanned = css`
        background-color: rgb(247, 247, 247);
        cursor: not-allowed;
        > svg {
            fill: rgb(178, 178, 178);
        }
    `;

    const childrenLength = useMemo(() => Children.toArray(children).length, [children]);

    return <div></div>;
};

export default CategorySwiper;
