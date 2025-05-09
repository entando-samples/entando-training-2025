import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from "@angular/common/http";

export const httpInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
    const token: string = 
            (window?.entando?.keycloak && window.entando.keycloak.authenticated) ? window?.entando?.keycloak?.token : ""
  

    if (!token) return next(req);

    const headers = {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
    }

    return next(req.clone({
        setHeaders: headers,
    }))
}