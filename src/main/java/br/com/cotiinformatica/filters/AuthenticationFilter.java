package br.com.cotiinformatica.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		List<String> urlsPermitidas = new ArrayList<String>();
		
		urlsPermitidas.add("/");
		urlsPermitidas.add("/post-login");
		
		urlsPermitidas.add("/register");
		urlsPermitidas.add("/post-register");
		
		urlsPermitidas.add("/forgot-password");
		urlsPermitidas.add("/post-resolution");
		
		String pagAtual = req.getServletPath();
		
		if(!urlsPermitidas.contains(pagAtual)) {//se pagAtual for diferente das 3 contidas na lista.
			if(req.getSession().getAttribute("usuario")==null) {
				//redirecionar para pag:: login
				res.sendRedirect("/projetoWeb01/");
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
