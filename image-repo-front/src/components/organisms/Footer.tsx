import { styled } from "styled-components";
import Navigation from "./Navigation";

type FooterProps = {}

const StyledFooter = styled.footer`

`;

const Footer: React.FunctionComponent<FooterProps> = (props) => {
    return (
        <StyledFooter>
            <section>
                <h4>
                    Pixel Port
                </h4>
                <Navigation />
            </section>
            <span />
            <h6>
                2077 Pixel Port. Todos direitos reservados.
            </h6>
        </StyledFooter>
    );
}

export default Footer;