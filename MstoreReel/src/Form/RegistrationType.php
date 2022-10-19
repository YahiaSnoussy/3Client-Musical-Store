<?php

namespace App\Form;

use App\Entity\Personne;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

use Gregwar\CaptchaBundle\Type\CaptchaType;

class RegistrationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('captcha' ,CaptchaType::class)
            ->add('nom')
            ->add('prenom')
            ->add('role')
            ->add('email')
            ->add('username')
            ->add('password' , PasswordType::class  )
            ->add('confirm_password' , PasswordType::class )
            ->add('photo', FileType::class ,array('data_class' => null))
            ->add('numTelephone')
            ->add('captcha' ,CaptchaType::class);

    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Personne::class,
        ]);
    }
}
