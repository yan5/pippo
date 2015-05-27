/*
 * Copyright (C) 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.pippo.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ro.pippo.controller.Controller;
import ro.pippo.demo.common.ContactService;

import javax.inject.Inject;

/**
 * @author Decebal Suiu
 */
public class ContactsController extends Controller {

//    @Inject
    @Autowired
    private ContactService contactService;

    @Value("${message}")
    private String message;

    public void index() {
        System.out.println("contactService = " + contactService);
        System.out.println("message = " + message);
        getResponse().bind("contacts", contactService.getContacts());
        getResponse().render("contacts");
    }

}
